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
    public class EstatusController : ControllerBase
    {
        private readonly TicketDbContext _context;

        public EstatusController(TicketDbContext context)
        {
            _context = context;
        }

        #region INTERNOS

        /* OBTENER TODOS LOS ESTATUS */
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Estatus>>> GetEstatus()
        {
            return await _context.Estatus.ToListAsync();
        }

        /* OBTENER ESTATUS POR ID */
        [HttpGet("{id}")]
        public async Task<ActionResult<Estatus>> GetEstatus(int id)
        {
            var estatus = await _context.Estatus.FindAsync(id);

            if (estatus == null)
            {
                return NotFound();
            }

            return estatus;
        }
        #endregion

    }
}
