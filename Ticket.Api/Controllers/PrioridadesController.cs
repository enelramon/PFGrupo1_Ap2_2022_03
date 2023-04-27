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
    public class PrioridadesController : ControllerBase
    {
        private readonly TicketDbContext _context;

        public PrioridadesController(TicketDbContext context)
        {
            _context = context;
        }

        #region INTERNOS
        /* OBTENER TODAS PRIORIDADES */
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Prioridades>>> GetPrioridades()
        {
            return await _context.Prioridades.ToListAsync();
        }

        /* OBTENER PRIORIDAD POR ID */
        [HttpGet("{id}")]
        public async Task<ActionResult<Prioridades>> GetPrioridades(int id)
        {
            var prioridades = await _context.Prioridades.FindAsync(id);

            if (prioridades == null)
            {
                return NotFound();
            }

            return prioridades;
        }

        #endregion
    }
}
