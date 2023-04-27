using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Ticket.Api.DAL;
using Ticket.Api.Models;

namespace Ticket.Api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class TiposController : ControllerBase
    {
        private readonly TicketDbContext _context;

        public TiposController(TicketDbContext context)
        {
            _context = context;
        }

        #region INTERNOS
        /* OBTENER TODOS LOS TIPOS */
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Tipos>>> GetTipos()
        {
            return await _context.Tipos.ToListAsync();
        }

        /* OBTENER TIPO POR ID */
        [HttpGet("{id}")]
        public async Task<ActionResult<Tipos>> GetTipos(int id)
        {
            var tipos = await _context.Tipos.FindAsync(id);

            if (tipos == null)
            {
                return NotFound();
            }

            return tipos;
        }
        #endregion        
    }
}
